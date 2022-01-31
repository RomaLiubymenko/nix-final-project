import {AfterViewInit, Component, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {BreakpointObserver} from "@angular/cdk/layout";
import {delay, Subscription, take} from "rxjs";
import {MenuItem} from "primeng/api";
import {ISelectModal} from '../../models/select.model';
import {AvailableLangs, TranslocoService} from "@ngneat/transloco";


@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, AfterViewInit, OnDestroy {

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  @Input()
  isLogin: boolean

  langs: ISelectModal[];
  selectedLang: ISelectModal = {name: 'en_EN', code: 'en'};

  sideNavNavigations: MenuItem[];
  menuBarNavigations: MenuItem[];

  private subscription: Subscription = Subscription.EMPTY;
  availableLangs: AvailableLangs;

  constructor(private translocoService: TranslocoService, private observer: BreakpointObserver) {
  }

  ngOnInit(): void {
    this.langs = [{name: 'en_EN', code: 'en'}, {name: 'ru_RU', code: 'ru'}];
    this.sideNavNavigations = [
      {
        label: 'Users',
        icon: 'pi pi-pw pi-users',
        items: [
          {
            label: 'Students',
            icon: 'pi pi-fw pi-user',
            routerLink: 'students'
          },
        ]
      },
    ];

    this.menuBarNavigations = [];

    this.translocoService.langChanges$.subscribe(lang =>  {

    });
  }

  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
  }

  get activeLang() {
    return this.translocoService.getActiveLang();
  }

  onChangeLang(lang: string) {
    this.subscription.unsubscribe();
    this.subscription = this.translocoService.load(lang)
      .pipe(take(1))
      .subscribe(() => {
        this.translocoService.setActiveLang(lang);
      });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
