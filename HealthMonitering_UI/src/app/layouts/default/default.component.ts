import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/modules/dashboard.service';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.scss']
})
export class DefaultComponent implements OnInit {

  sideBarOpen = true;

  constructor(private dashboardService:DashboardService) { }

  ngOnInit() { }


  sideBarToggler() {
    this.sideBarOpen = !this.sideBarOpen;
  }

  download(){

    this.dashboardService.getAllMeterData().subscribe(res =>{
      console.log(res.data)
      this.dashboardService.downloadFile(res.data);
    })

  }

}
