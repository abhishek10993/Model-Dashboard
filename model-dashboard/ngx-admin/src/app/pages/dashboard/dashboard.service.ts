import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable({
    providedIn: 'root'
})
export class DashboardService {

    constructor(private http:HttpClient) { }
    getRegressionDataMonthly() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth?name=Regression');
    }
    getClassificationDataMonthly() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth?name=Classification');
    }

    getClusteringDataMonthly() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth?name=Clustering');
    }
    getNeuralNetworkDataMonthly() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth?name=Neural%20Network');
    }
    getDataMonthly() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth');
    }

    getRegressionDataTotal() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth/total?name=Regression');
    }
    getClassificationDataTotal() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth/total?name=Classification');
    }

    getClusteringDataTotal() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth/total?name=Clustering');
    }
    getNeuralNetworkDataTotal() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth/total?name=Neural%20Network');
    }
    getDataTotal() {
        return this.http.get('http://192.168.221.25:8080/modelsbymonth/total');
    }
    getStaticData() {
        return this.http.get('http://192.168.221.25:8080/staticdata');
    }
    getAlgorithmsData(){
        return this.http.get('http://192.168.221.25:8080/algorithms'); //bar chart
    }
    getModelStatus(){
        return this.http.get('http://192.168.221.25:8080/modelstatus'); //new card
    }
    getModelsPerApplication(){
        return this.http.get('http://192.168.221.25:8080/modelsperapplication'); //new card
    }
    getModelsPerUser(){
        return this.http.get('http://192.168.221.25:8080/modelsperuser');
    }

    getSizebyAlgorithms(){
        return this.http.get('http://192.168.221.25:8080/sizebyalgorithms');
    }

    getAlgorithmsDatabyDate(to_date?: string,from_date? : string){
        if(to_date!=undefined){
            console.log("date not receieved");
        }
        return this.http.get('http://192.168.221.25:8080/algorithms?from_date='+from_date+'&to_date='+to_date); //bar chart
    }



}
