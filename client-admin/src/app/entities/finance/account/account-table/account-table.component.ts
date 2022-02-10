import {Component, OnInit} from '@angular/core';
import {DialogService} from "primeng/dynamicdialog";
import {AbstractTableComponent} from "../../../../shared/components/abstract-table/abstract-table.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslocoService} from "@ngneat/transloco";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {SelectModal} from "../../../../shared/models/select.model";
import * as moment from "moment";
import {Account, AccountOwnershipType, AccountType} from "../../../../shared/models/finance/account.model";
import {AccountFilter} from "../../../../shared/models/filters/finance/account-filter.model";
import {AccountService} from "../../../../shared/services/finance/account.service";
import {UserService} from "../../../../shared/services/user/user.service";
import {User} from "../../../../shared/models/user/user.model";

@Component({
  selector: 'account-table',
  templateUrl: './account-table.component.html',
  styleUrls: ['./account-table.component.scss'],
  providers: [DialogService]
})
export class AccountTableComponent extends AbstractTableComponent<Account, AccountFilter> implements OnInit {

  namespace = 'account';
  searchDate: Date | undefined;
  accountTypeForSelect: any[] = [];
  ownershipTypeForSelect: any[] = [];
  selectedAccountType: any;
  selectedOwnershipType: any;
  override filterEntity: AccountFilter = new AccountFilter();
  usersForSelect: any[] = [];
  selectedUserUuids: string[] = [];

  override allPropertyNames = [
    {field: 'name', selected: true},
    {field: 'description', selected: true},
    {field: 'accountChangedDate', selected: true},
    {field: 'balance', selected: true},
    {field: 'isBlocked', selected: true},
    {field: 'type', selected: true},
    {field: 'ownershipType', selected: true},
    {field: 'users', selected: true},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public accountService: AccountService,
    public userService: UserService,
    public override translocoService: TranslocoService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public dialogService: DialogService) {
    super(accountService, router, activatedRoute, messageService, confirmationService, translocoService);
  }

  override ngOnInit(): void {
    this.createUsersForSelect();
    this.setAccountTypeForSelect();
    this.setAccountOwnershipTypeForSelect();
    this.handleNavigation();
    this.setVisibleFields();
  }

  createUsersForSelect() {
    this.userService.getAll().subscribe(userRes => {
      userRes.forEach(user => this.usersForSelect.push(new SelectModal(user.lastName + ' ' + user.firstName, user.uuid)));
    });
  }

  addStudentAccount(): void {
    this.router.navigate(['accounts', 'new-account', {queryParams: AccountOwnershipType.STUDENT}])
  }

  addTutorAccount(): void {
    this.router.navigate(['accounts', 'new-account', {queryParams: AccountOwnershipType.TUTOR}])
  }


  editAccount(account: Account): void {
    this.router.navigate(['accounts', account.uuid, 'edit-form', {queryParams: account.ownershipType}])
  }

  override filter() {
    this.filterEntity.type = this.selectedAccountType ?? null;
    this.filterEntity.ownershipType = this.selectedOwnershipType ?? null;
    if (this.searchDate) {
      this.filterEntity.accountChangedDate = moment(this.searchDate).toDate().toISOString();
    }
    if (this.selectedUserUuids.length) {
      this.filterEntity.userUuids = this.selectedUserUuids;
    }
    super.filter();
  }

  override onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    if (!!data.sortField && data.sortField != 'users') {
      this.predicate = data.sortField;
    } else {
      this.predicate = 'created';
    }
    this.isAscending = data.sortOrder == 1;
    if (this.itemsPerPage != data.rows!) {
      this.itemsPerPage = data.rows!;
      this.transition();
      this.loadAll();
    } else {
      this.transition();
    }
  }

  setAccountTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.accountTypeForSelect = [{
        name: this.translocoService.translate('STANDARD'),
        code: AccountType.STANDARD
      }, {
        name: this.translocoService.translate('WITHDRAWAL'),
        code: AccountType.WITHDRAWAL
      }, {
        name: this.translocoService.translate('REPLENISHMENT'),
        code: AccountType.REPLENISHMENT
      }];
    });
  }

  setAccountOwnershipTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.ownershipTypeForSelect = [{
        name: this.translocoService.translate('STUDENT'),
        code: AccountOwnershipType.STUDENT
      }, {
        name: this.translocoService.translate('TUTOR'),
        code: AccountOwnershipType.TUTOR
      }, {
        name: this.translocoService.translate('ADMIN'),
        code: AccountOwnershipType.ADMIN
      }];
    });
  }

  override clear() {
    this.selectedAccountType = undefined;
    this.selectedOwnershipType = undefined;
    this.searchDate = undefined;
    this.selectedUserUuids = [];
    this.filterEntity = new AccountFilter();
    super.clear();
  }

  redirectToUser(account: Account, user: User) {
    if (account.ownershipType === AccountOwnershipType.STUDENT) {
      this.router.navigate(['students', user.uuid, 'edit-form'])
    }
    if (account.ownershipType === AccountOwnershipType.TUTOR) {
      this.router.navigate(['tutors', user.uuid, 'edit-form'])
    }
    if (account.ownershipType === AccountOwnershipType.ADMIN) {
      this.router.navigate(['admins', user.uuid, 'full-info'])
    }
  }

  override deleteSelectedElements() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('DELETION_WARNING_MESSAGE') + '?',
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.accountService.deleteEntities(this.selectedElementsForDelete).subscribe({
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
              detail: this.translocoService.translate('DELETE_USER_REQUIRED'),
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
}
