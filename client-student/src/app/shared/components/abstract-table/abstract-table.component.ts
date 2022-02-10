import {Directive, OnInit} from "@angular/core";
import {AbstractModel} from "../../models/abstract.model";
import {AbstractService} from "../../services/abstract.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {SearchWithPagination} from "../../utils/request/param-util";
import {HttpHeaders, HttpResponse} from "@angular/common/http";
import {IAbstractFilter} from "../../models/filters/abstract-filter.model";
import {TranslocoService} from "@ngneat/transloco";

@Directive()
export abstract class AbstractTableComponent<ENTITY extends AbstractModel, FILTER extends IAbstractFilter> implements OnInit {

  entities: ENTITY[] = [];
  selectedEntity!: ENTITY;
  selectedElementsForDelete: ENTITY[] = [];
  totalItems = 0;
  itemsPerPage = 5;
  page!: number;
  predicate = 'created';
  isAscending!: boolean;
  searchText = '';
  columns: any[] = [];
  itemsPerPages = [5, 10, 20, 50, 100];
  allPropertyNames: any[] = [];
  filterEntity: FILTER;
  queryFilterParams: any = {};
  displayFilters = false;
  displayFieldFilters: boolean = false;

  protected constructor(
    public entryService: AbstractService<ENTITY>,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public messageService: MessageService,
    public confirmationService: ConfirmationService,
    public translocoService: TranslocoService) {
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.setVisibleFields();
  }

  protected setVisibleFields() {
    for (let fields of this.allPropertyNames) {
      if (fields.selected) {
        this.columns.push({
          field: fields.field,
          header: fields.header
        });
      }
    }
  }

  handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).pipe(map(([data, params]) => {
      const page = params.get('page');
      this.page = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      this.predicate = sort[0];
      this.isAscending = sort[1] === 'asc';
      let query = params.get('query');
      if (!!query) {
        this.searchText = query;
      }
      this.selectedElementsForDelete.length = 0;
      this.loadAll();
    })).subscribe();
  }

  transition(): void {
    if (Array.isArray(this.entities)) {
      let queryParams: { page?: number, sort?: string, query?: string } = {
        page: this.page,
        sort: this.predicate + ',' + (this.isAscending ? 'asc' : 'desc'),
      };
      if (this.searchText) {
        queryParams.query = this.searchText;
      }
      this.router.navigate(['./'], {
        relativeTo: this.activatedRoute.parent,
        queryParams: queryParams,
      });
    }
  }

  loadAll(): void {
    let requestParams: SearchWithPagination = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    if (this.queryFilterParams !== undefined) {
      if (!!this.queryFilterParams.fieldNames && !!this.queryFilterParams.fieldValues) {
        requestParams.fieldNames = this.queryFilterParams.fieldNames;
        requestParams.fieldValue = this.queryFilterParams.fieldValues;
      }
    }
    if (!!this.searchText) {
      requestParams.query = this.searchText;
    }
    this.entryService.getAllByQuery(requestParams).subscribe({
      next: (res: HttpResponse<ENTITY[]>) => this.onSuccess(res.body, res.headers),
      error: err => console.error(err)
    });
  }

  sort(): string[] {
    return [this.predicate + ',' + (this.isAscending ? 'asc' : 'desc')];
  }

  searchItems(keyword: string) {
    this.searchText = keyword;
    setTimeout(() => {
      this.transition();
    }, 1000);
  }

  selectRow(element: ENTITY) {
    this.selectedEntity = element;
  }

  onSuccess(entities: ENTITY[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    if (!!entities)
      this.entities = entities;
    this.displayFilters = false;
  }

  deleteSelectedElements() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('DELETION_WARNING_MESSAGE') + '?',
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entryService.deleteEntities(this.selectedElementsForDelete).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: this.translocoService.translate('SUCCESSFUL'),
              detail: this.translocoService.translate('ENTRIES_DELETED_SUCCESSFULLY'),
              life: 3000
            });
            this.selectedElementsForDelete.length = 0;
            this.loadAll()
          },
          error: err => {
            this.messageService.add({
              severity: 'error',
              summary: this.translocoService.translate('ERROR'),
              detail: err.error.error.join('\n'),
              life: 20000
            });
          }
        });
      },
      reject: () => {
        this.confirmationService.close();
      }
    });
  }

  fieldsFilters(selectedColumn: any) {
    const index = this.columns.findIndex(column => column.field == selectedColumn.field);
    if (index > -1) {
      this.columns.splice(index, 1);
    } else {
      this.columns.push(selectedColumn)
    }
  }

  getMinItemsOnCurrPage(): number {
    if (this.totalItems != 0) {
      if (this.page == 1) {
        return 1
      }
      return 1 + this.itemsPerPage * (this.page - 1);
    }
    return -1;
  }

  getMaxItemsOnCurrPage(): number {
    if (this.totalItems != 0) {
      if (this.page == 1) {
        return this.itemsPerPage > this.totalItems ? this.totalItems : this.itemsPerPage
      } else if (this.itemsPerPage * this.page > this.totalItems) {
        return this.totalItems
      } else {
        return this.itemsPerPage * this.page;
      }
    }
    return -1;
  }

  filter() {
    let fieldNames = [];
    let fieldValues = [];
    for (const key in this.filterEntity) {
      if (this.filterEntity[key]) {
        fieldNames.push(key);
        fieldValues.push(this.filterEntity[key]);
      }
    }
    this.queryFilterParams.fieldNames = fieldNames;
    this.queryFilterParams.fieldValues = fieldValues;
    this.loadAll();
  }

  openFilters() {
    this.displayFilters = true;
  }

  openFieldFilters() {
    this.displayFieldFilters = true;
  }

  onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    this.predicate = data.sortField ?? 'created';
    this.isAscending = data.sortOrder == 1;
    if (this.itemsPerPage != data.rows!) {
      this.itemsPerPage = data.rows!;
      this.transition();
      this.loadAll();
    } else {
      this.transition();
    }
  }
}
