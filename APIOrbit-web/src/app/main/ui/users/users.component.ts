import { UserModel } from '@/main/interfaces/user.model';
import { UsersService } from '@/main/service/users.service';
import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Table, TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-users',
  imports: [TableModule, InputIconModule, IconFieldModule, SelectModule, FormsModule, CommonModule, TagModule, InputTextModule, ButtonModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit {
  users: UserModel[] = [];
  loading: boolean = true;
  statuses: any[] = [];

  @ViewChild('filter') filter!: ElementRef;

  constructor(private userService : UsersService, private _toastrService: ToastrService){}

  ngOnInit(): void {
    this.userService.listAllUsers().subscribe((data : UserModel[]) => {
      this.users = data;
      this.loading = false;
    })
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
  
   clear(table: Table) {
        table.clear();
        this.filter.nativeElement.value = '';
   }
  
     getSeverity(status: string) {
        switch (status) {
            case 'qualified':
            case 'instock':
            case 'INSTOCK':
            case 'DELIVERED':
            case 'delivered':
          case 'admin':
            case 'active':
                return 'success';

            case 'negotiation':
            case 'lowstock':
            case 'LOWSTOCK':
            case 'PENDING':
            case 'pending':
                return 'warn';

            case 'unqualified':
            case 'outofstock':
            case 'OUTOFSTOCK':
            case 'CANCELLED':
          case 'cancelled':
            case 'deactive':
            return 'danger';

          case 'editor':
            return 'secondary';
          
          case 'viewer':
            return 'contrast';

            default:
                return 'info';
        }
     }
  
  
  activateUser(id : string) : void {
  throw new Error('Method not implemented.');
  }


  approveUser(id : string) : void {
    this.userService.approveUser(id).subscribe((user : UserModel) => {
       this._toastrService.success(
                'You have successfully approved the user as ' +
                  user.role,
                '👋 they can now login using the email, ' + user.email,
                { toastClass: 'toast ngx-toastr', closeButton: true }
              );
      const index = this.users.findIndex((e: UserModel) => e.uuid == user.uuid);
      this.users[index] = user;
    })
  }

  blockUser(id : string) : void {
    this.userService.disableUser(id).subscribe((user : UserModel) => {
       this._toastrService.success(
                'You have successfully disable the user as ' +
                  user.role,
                '👋 they can no longer login using the email, ' + user.email,
                { toastClass: 'toast ngx-toastr', closeButton: true }
              );
      const index = this.users.findIndex((e: UserModel) => e.uuid == user.uuid);
      this.users[index] = user;
    })
  }
}
