import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { MeterData } from '../modals/meterData';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private host = "http://localhost:5000";

  bigChart = [{
    name: 'DEVICE_1',
    data: []
  }, {
    name: 'DEVICE_2',
    data: []
  }, {
    name: 'DEVICE_3',
    data: []
  }];

  constructor(private _http: HttpClient) { }
  cards() {
    return [71, 78, 39, 66];
  }

  getAllMeterData(): Observable<any>{
    return this._http.get<any>(`${this.host}/getSensorData`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getDataByTime(): Observable<any>{
    return this._http.get<any>(`${this.host}/getSensorDataByTime`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  setThreshold(value): Observable<any>{
    return this._http.get<any>(`${this.host}/setThreshold?threshold=${value}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  downloadFile(data, filename='data') {
    let csvData = this.ConvertToCSV(data, ['deviceId','spo2', 'timeStamp', 'latitude', 'longitude','deviceBattery']);
    console.log(csvData)
    let blob = new Blob(['\ufeff' + csvData], { type: 'text/csv;charset=utf-8;' });
    let dwldLink = document.createElement("a");
    let url = URL.createObjectURL(blob);
    let isSafariBrowser = navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1;
    if (isSafariBrowser) {  //if Safari open in new window to save file with random filename.
        dwldLink.setAttribute("target", "_blank");
    }
    dwldLink.setAttribute("href", url);
    dwldLink.setAttribute("download", filename + ".csv");
    dwldLink.style.visibility = "hidden";
    document.body.appendChild(dwldLink);
    dwldLink.click();
    document.body.removeChild(dwldLink);
}
ConvertToCSV(objArray, headerList) {
     let array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
     let str = '';
     let row = 'S.No,';
for (let index in headerList) {
         row += headerList[index] + ',';
     }
     row = row.slice(0, -1);
     str += row + '\r\n';
     for (let i = 0; i < array.length; i++) {
         let line = (i+1)+'';
         for (let index in headerList) {
            let head = headerList[index];
line += ',' + array[i][head];
         }
         str += line + '\r\n';
     }
     return str;
 }


  handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert("Please check your internet connection!.");
    return throwError(errorMessage);
 }


}
