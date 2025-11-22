import { APIModel } from '@/main/interfaces/api.model';
import { ApiService } from '@/main/service/api.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';

import { EditorModule, EditorTextChangeEvent } from 'primeng/editor';
import { MessageModule } from 'primeng/message';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-documentation',
  imports: [EditorModule, FormsModule, CommonModule, MessageModule, CardModule, ButtonModule, BadgeModule, TagModule, DialogModule],
  templateUrl: './documentation.component.html',
  styleUrl: './documentation.component.css'
})
export class DocumentationComponent implements OnInit {

  text: any = "<p>asdasdwqeqwe&nbsp;test&nbsp;user</p>";
  apis: APIModel[] = [];
  docDialog: boolean = false;
  
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getAllApi().subscribe((apis: APIModel[]) => {
      this.apis = apis;
    })
  }
  

  onTextChange(event: EditorTextChangeEvent) {
    // The 'text' property of the event object contains the plain text
    console.log(event.htmlValue);

  }


  onSubmit(text: any) {
    this.text = text;
   console.log(text)
// throw new Error('Method not implemented.');
}

  openDialog(api: APIModel): void {
    this.docDialog = true;
  }

  hideDialog(): void {
    this.docDialog = false;
  }

  savedoc(text: any) {
    this.text = text;
    this.hideDialog();
  }

}
