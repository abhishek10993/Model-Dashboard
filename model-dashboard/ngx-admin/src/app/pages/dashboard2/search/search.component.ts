import { Component, OnDestroy,OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { NbDateService } from '@nebular/theme';
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnDestroy {

    colorScheme: any;
    themeSubscription: any;
    hoveredDate: NgbDate;
        result1 = [
        { result: 'Clustering Massive Data', similarity: '90%' },
        { result: 'Massive text analysis', similarity: '82%'  },
        { result: 'Classifying big data', similarity: '78%' },
        { result: 'Reinforced learning', similarity: '86%'  },
        { result: 'Learning from Big data', similarity: '88%' },
        { result: 'Handling challenges in learning ', similarity: '72%' }
    ]
    words1=['Learning','Massive','Challenges','Clustering','Classification'];
    keywords;
    searchResult;


    constructor(private theme: NbThemeService,calendar: NgbCalendar) {
        this.themeSubscription = this.theme.getJsTheme().subscribe(config => {
            const colors: any = config.variables;
            this.colorScheme = {
                domain: [colors.primaryLight, colors.infoLight, colors.successLight, colors.warningLight, colors.dangerLight],
            };
        });

}


    ngOnDestroy(): void {
        this.themeSubscription.unsubscribe();
    }
    search(f: NgForm) {
    	console.log(f.value.goal);
    	console.log(f.value.creator);
        this.searchResult=this.result1;
        this.keywords=Object.assign([], this.words1);
    }
    deleteWord(i){
    	this.keywords.splice(i, 1);
    	console.log(this.keywords);
    	console.log(this.words1);
    }

}
