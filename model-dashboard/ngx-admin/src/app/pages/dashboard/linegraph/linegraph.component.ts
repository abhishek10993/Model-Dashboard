import { Component, OnDestroy,OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { NbDateService } from '@nebular/theme';
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import {DashboardService} from '../dashboard.service';

@Component({
  selector: 'linegraph',
  templateUrl: './linegraph.component.html',
  styleUrls: ['./linegraph.component.scss']
})
export class LinegraphComponent implements OnDestroy {
    All_pie = [
        { name: 'PMML', value: 70 },
        { name: 'PFA', value: 30 },
        { name: 'Scikit-Learn', value: 20 },
        { name: 'R-Model', value: 10 },
    ]
    Regression_pie = [
        { name: 'PMML', value: 46 },
        { name: 'PFA', value: 4 },
        { name: 'Scikit-Learn', value: 10 },
        { name: 'R-Model', value: 4 },
    ]
    Classification_pie = [
        { name: 'PMML', value: 12 },
        { name: 'PFA', value: 3 },
        { name: 'Scikit-Learn', value: 1 },
        { name: 'R-Model', value: 0 },
    ]
    NeuralNetwork_pie = [
        { name: 'PMML', value: 41 },
        { name: 'PFA', value: 1 },
        { name: 'Scikit-Learn', value: 2 },
        { name: 'R-Model', value: 3 },
    ]
    Clustering_pie = [
        { name: 'PMML', value: 41 },
        { name: 'PFA', value: 2 },
        { name: 'Scikit-Learn', value: 1 },
        { name: 'R-Model', value: 1 },
    ]
    doughnut= false;
    explodeSlices=false;
    showLabels= true;
    showLegends=true;

    filter="Regression";
    show:boolean = false;
    datefilter:boolean=false;

    results_pie=this.All_pie;


    All_bar:any=[];
    All_bar_date_filter = [
        { name: 'Classification', value: 10 },
        { name: 'Regression', value: 20 },
        { name: 'Clustering', value: 50 },
        { name: 'Neural Network', value: 10 }
    ]
    All_pie_date_filter = [
        { name: 'PMML', value: 10 },
        { name: 'PFA', value: 20 },
        { name: 'Scikit-Learn', value: 50 },
        { name: 'R-Model', value: 12 },
    ]

    results_bar=this.All_bar;
    flipped = false;

    toggleView() {
        this.flipped = !this.flipped;
    }


    contributor_all = [
        { name: 'Nick', value: 50 },
        { name: 'Eva', value: 48 },
        { name: 'Uwe', value: 61 },
        { name: 'Alan', value: 46 },
        { name: 'Peter', value: 53 }
    ]
    contributor_regression = [
        { name: 'Nick', value: 10 },
        { name: 'David', value: 12 },
        { name: 'Mack', value: 20 },
        { name: 'Alan', value: 10 },
        { name: 'Eva', value: 11 }
    ]
    contributor_classification = [
        { name: 'Tina', value: 18 },
        { name: 'Anni', value: 14 },
        { name: 'Uwe', value: 12 },
        { name: 'David', value: 16 },
        { name: 'Mack', value: 22 }
    ]
    contributor_clustering = [
        { name: 'Alan', value: 11 },
        { name: 'Eva', value: 14 },
        { name: 'Uwe', value: 18 },
        { name: 'Nick', value: 11 },
        { name: 'Tina', value: 21 }
    ]
    contributor_neural = [
        { name: 'David', value: 17 },
        { name: 'Eva', value: 6 },
        { name: 'Anni', value: 8 },
        { name: 'Alan', value: 12 },
        { name: 'Uwe', value: 4 }
    ]

result_contributor=this.contributor_all;


control="Monthly";
showDataLabel = true;
showLegend = true;
showXAxis = true;
showYAxis = true;
xAxisLabel = 'Country';
yAxisLabel = 'Population';
    colorScheme: any;
    customColors_pie = [
        {
            name: 'PMML',
            value: '#4396DE'
        },{
            name: 'PFA',
            value: '#B2EBE0'
        },
        {
            name: 'Scikit-Learn',
            value: '#5E96AE'
        },
        {
            name: 'R-Model',
            value: '#38908F'
        }
    ];

    customColors_bar = [
        {
            name: 'Jan',
            value: '#4396DE'
        },
        {
            name: 'Feb',
            value: '#4396DE'
        },
        {
            name: 'Mar',
            value: '#4396DE'
        },
        {
            name: 'Apr',
            value: '#4396DE'
        },
        {
            name: 'May',
            value: '#4396DE'
        },
        {
            name: 'Jun',
            value: '#4396DE'
        },
        {
            name: 'Jul',
            value: '#4396DE'
        },
        {
            name: 'Aug',
            value: '#4396DE'
        },
        {
            name: 'Sep',
            value: '#4396DE'
        },
        {
            name: 'Oct',
            value: '#4396DE'
        },
        {
            name: 'Nov',
            value: '#4396DE'
        },
        {
            name: 'Dec',
            value: '#4396DE'
        },

    ];

    sizes_by_category:any=[];

    All_Monthly:any=[];
    result=this.All_Monthly;
    Classification_Monthly:any= [];
    Regression_Monthly:any=[]
    NeuralNetwork_Monthly:any=[];
    Clustering_Monthly:any=[];

    All_Total:any=[];
    Classification_Total:any= [];
    Regression_Total:any=[]
    NeuralNetwork_Total:any=[];
    Clustering_Total:any=[];



    barPadding=40;
    models_barPadding=45;
    themeSubscription: any;
    hoveredDate: NgbDate;
    to_date:string;
    from_date:string;

    fromDate: NgbDate;
    toDate: NgbDate;
    colorscheme_bar={domain : ['#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE','#4396DE']};
    colorscheme_bar_models={domain: ['#4396DE','#4396DE','#4396DE','#4396DE']};
    colorscheme_bar_models_all=this.colorscheme_bar_models;
    colorscheme_classification_faded={domain: ['#4396DE','#E9EBED','#E9EBED','#E9EBED']};
    colorscheme_regression_faded={domain: ['#E9EBED','#4396DE','#E9EBED','#E9EBED']};
    colorscheme_clustering_faded={domain: ['#E9EBED','#E9EBED','#4396DE','#E9EBED']};
    colorscheme_neuralnetworks_faded={domain: ['#E9EBED','#E9EBED','#E9EBED','#4396DE']};


    onDateSelection(date: NgbDate) {
        if (!this.fromDate && !this.toDate) {
            this.fromDate = date;
        } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
            this.toDate = date;
            this.to_date=this.toDate.year+"-"+this.toDate.month+"-"+this.toDate.day;
            this.from_date=this.fromDate.year+"-"+this.fromDate.month+"-"+this.fromDate.day;
            this.getAlgorithmsDatabyDate(this.to_date,this.from_date);
            if (this.results_pie ==this.All_pie){
                this.results_pie=this.All_pie_date_filter;
            }
            else {
                this.results_pie=this.All_pie;
            }
            if (this.results_bar ==this.All_bar){
                this.results_bar=this.All_bar_date_filter;
            }
            else {
                this.results_bar=this.All_bar;
            }
        } else {
            this.toDate = null;
            this.fromDate = date;
        }
    }

    arraysEqual(value,other) {
        return JSON.stringify(value) === JSON.stringify(other);
    }

    isHovered(date: NgbDate) {
        return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
    }

    isInside(date: NgbDate) {
        return date.after(this.fromDate) && date.before(this.toDate);
    }

    isRange(date: NgbDate) {
        return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
    }
min: Date;
max: Date;

    getSizebyAlgorithms(){
        this.demoService.getSizebyAlgorithms().subscribe((data: {}) => {
            this.sizes_by_category = data;
        });
    }

    getDataMonthly() {
        this.demoService.getDataMonthly().subscribe((data: {}) => {
            this.All_Monthly=data;
            this.result = data;
        });
    }

    getRegressionDataMonthly() {
        this.demoService.getRegressionDataMonthly().subscribe((data: {}) => {
            this.Regression_Monthly = data;
        });
    }


    getClassificationDataMonthly() {

        this.demoService.getClassificationDataMonthly().subscribe((data: {}) => {
            this.Classification_Monthly = data;
        });
    }

    getNeuralNetworkDataMonthly() {

        this.demoService.getNeuralNetworkDataMonthly().subscribe((data: {}) => {
            this.NeuralNetwork_Monthly = data;
        });
    }

    getClusteringDataMonthly() {

        this.demoService.getClusteringDataMonthly().subscribe((data: {}) => {
            this.Clustering_Monthly = data;
        });
    }


    getRegressionDataTotal() {
        this.demoService.getRegressionDataTotal().subscribe((data: {}) => {
            this.Regression_Total = data;
        });
    }


    getClassificationDataTotal() {
        this.demoService.getClassificationDataTotal().subscribe((data: {}) => {
            this.Classification_Total = data;
        });
    }

    getNeuralNetworkDataTotal() {

        this.demoService.getNeuralNetworkDataTotal().subscribe((data: {}) => {
            this.NeuralNetwork_Total = data;
        });
    }

    getClusteringDataTotal() {

        this.demoService.getClusteringDataTotal().subscribe((data: {}) => {
            this.Clustering_Total = data;
        });
    }

    getDataTotal() {
        this.demoService.getDataTotal().subscribe((data: {}) => {
            this.All_Total=data;
            this.result = data;
        });
    }

    getAlgorithmsData() {
        this.demoService.getAlgorithmsData().subscribe((data: {}) => {
            this.results_bar = data;
        });
    }

    getAlgorithmsDatabyDate(to_date:string,from_date:string){
        this.demoService.getAlgorithmsDatabyDate(to_date,from_date).subscribe((data: {}) => {
            this.results_bar = data;
        });
    }


    constructor(private theme: NbThemeService,calendar: NgbCalendar,private demoService: DashboardService) {
        this.themeSubscription = this.theme.getJsTheme().subscribe(config => {
            const colors: any = config.variables;
            this.colorScheme = {
                domain: [colors.primaryLight, colors.infoLight, colors.successLight, colors.warningLight, colors.dangerLight],
            };
        });
        this.fromDate = calendar.getToday();
        this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
        this.getDataMonthly();
        this.getClassificationDataMonthly();
        this.getClusteringDataMonthly();
        this.getRegressionDataMonthly();
        this.getNeuralNetworkDataMonthly();
        this.getClusteringDataTotal();
        this.getRegressionDataTotal();
        this.getNeuralNetworkDataTotal();
        this.getClassificationDataTotal();
        this.getAlgorithmsData();
        this.getSizebyAlgorithms();
    }


    ngOnDestroy(): void {
        this.themeSubscription.unsubscribe();
    }


    onBarSliceSelect(event){

        console.log(event.name);
        var value=event.name;
        this.show=true;
    if (value=='Regression') {
        this.filter="Regression";
        this.colorscheme_bar_models_all=this.colorscheme_regression_faded;

        if(this.control=="Monthly"){

            this.getRegressionDataMonthly();
          this.result=this.Regression_Monthly;
      }
        if(this.control=="Total"){

            this.getRegressionDataTotal();
          this.result=this.Regression_Total;
      }

        this.results_pie=this.Regression_pie;
        this.result_contributor=this.contributor_regression;
      }
      if (value=='Classification') {
          this.filter="Classification";
          this.colorscheme_bar_models_all=this.colorscheme_classification_faded;
          if(this.control=="Monthly"){
              this.getClassificationDataMonthly();
          this.result=this.Classification_Monthly;
      }
          if(this.control=="Total"){
              this.getClassificationDataTotal();
                this.result=this.Classification_Total;
      }
            this.results_pie=this.Classification_pie;
            this.result_contributor=this.contributor_classification;

    }
     if (value=='Clustering') {
         this.filter="Clustering";
         this.colorscheme_bar_models_all=this.colorscheme_clustering_faded;
         if(this.control=="Monthly"){
             this.getClusteringDataMonthly();
                this.result=this.Clustering_Monthly;
      }
         if(this.control=="Total"){
             this.getClusteringDataTotal();
                this.result=this.Clustering_Total;
      }
         this.results_pie=this.Clustering_pie;
         this.result_contributor=this.contributor_clustering;
    }
      if (value=='Neural Network') {
          this.filter="Neural Network";
          this.colorscheme_bar_models_all=this.colorscheme_neuralnetworks_faded;
          if(this.control=="Monthly"){
              this.getNeuralNetworkDataMonthly();
                this.result=this.NeuralNetwork_Monthly;
      }
          if(this.control=="Total"){
              this.getNeuralNetworkDataTotal();
                this.result=this.NeuralNetwork_Total;
      }
                this.results_pie=this.NeuralNetwork_pie;
                this.result_contributor=this.contributor_neural;
    }
    }
    handleChange(evt){
        var target = evt.target;
        console.log(target.value);
           if (target.value=="delta") {
               this.control="Monthly";

               if(this.arraysEqual(this.result,this.Clustering_Total)){
               this.result=this.Clustering_Monthly;
             }
               else if(this.arraysEqual(this.result,this.Regression_Total)){

               this.result=this.Regression_Monthly;
             }
               else if(this.arraysEqual(this.result,this.NeuralNetwork_Total)){
               this.result=this.NeuralNetwork_Monthly;
             }
               else if(this.arraysEqual(this.result,this.Classification_Total)){
               this.result=this.Classification_Monthly;
               }
               else {
                   this.getDataMonthly();
               }
           }
            if (target.value=="total") {
                this.control="Total";

                if(this.arraysEqual(this.result,this.Clustering_Monthly)){
                    this.result=this.Clustering_Total;
                } else if(this.arraysEqual(this.result,this.Regression_Monthly)){

                 this.result=this.Regression_Total;
             } else if(this.arraysEqual(this.result,this.NeuralNetwork_Monthly)){

                 this.result=this.NeuralNetwork_Total;
             } else if(this.arraysEqual(this.result,this.Classification_Monthly)){

                 this.result=this.Classification_Total;
                } else {
                    this.getDataTotal();
                }
           }

    }
        deleteSkill() {
        console.log("Got click");
        console.log(this.control);
        this.show=false;
        this.colorscheme_bar_models_all=this.colorscheme_bar_models;
        if(this.control=="Monthly"){
            this.getDataMonthly();
      }
      if(this.control=="Total"){
          this.getDataTotal();
      }
                this.results_pie=this.All_pie;
                this.result_contributor=this.contributor_all;
    }
    opendatefilter(){
      this.datefilter=true;
    }
    hidedatefilter(){
        this.datefilter=false;
        this.getAlgorithmsData();
    }

}
