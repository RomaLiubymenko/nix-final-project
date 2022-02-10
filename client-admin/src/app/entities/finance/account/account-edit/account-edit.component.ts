import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../../../../shared/services/user/student.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {TranslocoService} from "@ngneat/transloco";
import {SelectModal} from "../../../../shared/models/select.model";
import {User} from "../../../../shared/models/user/user.model";
import {Account, AccountOwnershipType, AccountType} from "../../../../shared/models/finance/account.model";
import {AccountService} from "../../../../shared/services/finance/account.service";
import {TutorService} from 'src/app/shared/services/user/tutor.service';
import {AdminService} from "../../../../shared/services/user/admin.service";

@Component({
  selector: 'account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.scss']
})
export class AccountEditComponent implements OnInit {

  account: Account;
  selectedUserUuids: string[] = [];
  usersForSelect: any[] = [];
  users: User[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService,
    private tutorService: TutorService,
    private adminService: AdminService,
    private accountService: AccountService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private translocoService: TranslocoService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({data}) => {
      data['account'].subscribe((account: Account) => {
        this.account = account;
        const userType = data['userType'];
        this.account.type = AccountType.STANDARD;
        this.setSelectedUser();
        if (userType === AccountOwnershipType.ADMIN) {
          this.createAdminForSelect();
          this.account.ownershipType = userType;
        }
        if (userType === AccountOwnershipType.STUDENT) {
          this.createStudentForSelect();
          this.account.ownershipType = userType;
        }
        if (userType === AccountOwnershipType.TUTOR) {
          this.createTutorForSelect();
          this.account.ownershipType = userType;
        }
      });
    });
  }

  save() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('CONFIRMATION_TO_SAVE_ENTITY'),
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedUserForSave();
        this.account.accountChangedDate = new Date().toISOString();
        this.accountService.save(this.account).subscribe({
          next: () => this.router.navigate(['accounts']),
          error: err => {
            this.messageService.add({
              severity: 'error',
              summary: this.translocoService.translate('ERROR'),
              detail: this.translocoService.translate('ONLY_ADD_USERS'),
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

  setSelectedUser() {
    this.selectedUserUuids = this.account.users!?.map(user => user.uuid!);
  }

  createStudentForSelect() {
    this.studentService.getAll().subscribe(students => {
      this.users = students.map(student => {
        this.usersForSelect.push(new SelectModal(student.lastName + ' ' + student.firstName, student.uuid));
        return student;
      })
      this.isLoading = false;
    });
  }

  createTutorForSelect() {
    this.tutorService.getAll().subscribe(tutors => {
      this.users = tutors.map(tutor => {
        this.usersForSelect.push(new SelectModal(tutor.lastName + ' ' + tutor.firstName, tutor.uuid));
        return tutor;
      })
      this.isLoading = false;
    });
  }

  createAdminForSelect() {
    this.adminService.getAll().subscribe(admins => {
      this.users = admins.map(admin => {
        this.usersForSelect.push(new SelectModal(admin.lastName + ' ' + admin.firstName, admin.uuid));
        return admin;
      })
      this.isLoading = false;
    });
  }

  preparedUserForSave() {
    if (this.selectedUserUuids.length) {
      this.account.users = this.users.filter(user => this.selectedUserUuids.includes(user.uuid!));
    } else {
      this.account.users = [];
    }
  }
}
