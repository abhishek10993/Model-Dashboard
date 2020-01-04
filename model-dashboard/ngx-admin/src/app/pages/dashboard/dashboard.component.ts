import { Component,OnInit } from '@angular/core';
import {DashboardService} from './dashboard.service';

@Component({
  selector: 'ngx-dashboard',
  templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
    products:any = [];
    constructor(private demoService: DashboardService) { }
    ngOnInit() {
        this.getStaticData();
        this.getModelStatus();
        this.getModelPerApplication();
    }
    static_data:any={};
    model_status:any={};
    models_per_application:any={};
    getStaticData() {
        this.demoService.getStaticData().subscribe((data: {}) => {
            this.static_data=data;
        });
    }

    getModelStatus(){
        this.demoService.getModelStatus().subscribe((data: {}) => {
            this.model_status=data;
        });
    }

    getModelPerApplication(){
        this.demoService.getModelsPerApplication().subscribe((data: {}) => {
            this.models_per_application=data;
        });
    }

}
