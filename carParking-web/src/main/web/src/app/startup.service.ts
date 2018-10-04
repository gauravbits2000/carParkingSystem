import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class StartupService {

    userNames: string[];

    constructor(private httpClient: HttpClient) { }

        load(): Promise<any> {

        this.userNames = null;
        return this.httpClient
            .get('http://localhost:8080//markit-car-parking/fetch-markit-employees')
            .toPromise()
            .then((data: any) => this.userNames = data)
            .catch((err: any) => Promise.resolve());
    }

    getAllMarkitEmployee(): any {
        return this.userNames;
    }
}