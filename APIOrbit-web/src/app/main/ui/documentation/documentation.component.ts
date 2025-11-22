import { APIModel } from '@/main/interfaces/api.model';
import { ApiService } from '@/main/service/api.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

import { EditorModule, EditorTextChangeEvent } from 'primeng/editor';
import { MessageModule } from 'primeng/message';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-documentation',
  imports: [EditorModule, FormsModule, CommonModule, MessageModule, CardModule, ButtonModule, BadgeModule, TagModule],
  templateUrl: './documentation.component.html',
  styleUrl: './documentation.component.css'
})
export class DocumentationComponent implements OnInit {

  text: any = "<p>asdasdwqeqwe&nbsp;test&nbsp;user</p>";
  apis: APIModel[] = [];

    data = {
    id: "f8a01fa3-f49a-4bf2-8e8f-69cf5b172b9d",
    name: "asdasdqwewqe",
    description: "asdasdqwewqe",
    category: "finance",
    tags: "asdasdqwewqe",
    urlBase: "asdasdqwewqe",
    version: 0.0,
    status: "PENDING",
    authType: "asdasdqwewqe",
    documentationUrl: "www.test.come/docs",
    mockUrl: "www.test.come/mock",
    createdBy: "admin@example.com",
    approvedBy: "asdasdqwewqe",
    updatedDate: "2025-11-21T08:30:29.768+00:00",
    instructions: "asadasdqweqwewqeqwe"
    };
  
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

}
