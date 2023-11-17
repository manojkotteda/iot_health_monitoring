import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { switchMap, takeUntil, catchError, map } from 'rxjs/operators';
import { Observable, of, Subject, Subscription, timer } from 'rxjs';
import { MeterData } from 'src/app/modals/meterData';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {

  constructor(private dashboardService: DashboardService, private _route: ActivatedRoute) {

    this.chartData = this._route.snapshot.data['bigChartResolvedData'];

    this.chartData.data.forEach(ele => {

      if (ele.deviceId == "DEVICE_1") {
        this.bigChart[0].data.push(ele.spo2);
      }
    })
    //console.log(this.bigChart)

  }

  chartData: any;

  bigChart = [{
    name: 'DEVICE_1',
    data: []
  }];
  cards = [];

  displayedColumns: string[] = ['deviceId', 'spo2', 'deviceBattery', 'timeStamp'];
  dataSource = new MatTableDataSource<MeterData>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  consumptionData=[]
  maxConsumprion:number=0;
  minConsumption:number=0;
  avgConsumption:number=0;
  dataByTime=[];

  ngOnInit() {
    this.cards = this.dashboardService.cards();
    this.dataSource.paginator = this.paginator;

    this.subscription = timer(0, 10000).pipe(
      switchMap(() => this.dashboardService.getAllMeterData())
    ).subscribe(result => {
      this.dataSource.data = result.data;
      //console.log(this.maxConsumprion)
    });

    this.subscription1 = timer(0, 10000).pipe(
      switchMap(() => this.dashboardService.getDataByTime())
    ).subscribe(result => {
      this.dataByTime = result.data;

      this.dataByTime.forEach(ele=>{
       this.consumptionData.push(ele.spo2.toFixed(3));
      })
      this.maxConsumprion = Math.max(...this.consumptionData);
      this.minConsumption = Math.min(...this.consumptionData);
      this.avgConsumption = ((this.maxConsumprion+this.minConsumption)/2.0);
      //console.log(this.maxConsumprion)
    });
  }

  subscription1:Subscription;

  subscription: Subscription;
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }



}
