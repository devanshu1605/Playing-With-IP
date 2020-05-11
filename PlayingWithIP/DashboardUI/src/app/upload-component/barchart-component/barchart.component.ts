import { Component, OnInit, Input } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';
import { FileUploadResponse } from '../../model/ResponseModel';

@Component({
  selector: 'app-barchart',
  templateUrl: './barchart-component.html'
})
export class BarChartComponent implements OnInit {

  data: Array<number>;
  label: string = "devanshu";
  @Input() response: FileUploadResponse;

  barChartLabels: Label[];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataSets[];
  barChartOptions: ChartOptions = {
    responsive: true,
  };

  ngOnInit(): void {
    console.log(" printing response " + JSON.stringify(this.response.
    this.response.sortedFileMap.forEach((value: number, key: string) => {
      console.log("printing key value "+key+" "+ value);
      this.barChartLabels.push(key);
      this.data.push(value);
    });
    this.barChartData = [{ data: this.data, label: this.label }];
    
  }
}
