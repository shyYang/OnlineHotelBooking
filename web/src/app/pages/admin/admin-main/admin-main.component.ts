import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit {
  text='admin';
  color='#f56a00';

  constructor() { }

  ngOnInit(): void {
  }

}
