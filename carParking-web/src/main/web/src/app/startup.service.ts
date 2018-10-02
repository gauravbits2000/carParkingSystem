import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class StartupService {

    userNames: string[];
    username : string = 'XXXX';
    password : string = 'XXXXX';

    constructor(private httpClient: HttpClient) { }

        load(): Promise<any> {

        this.userNames = null;
        const params = {username: this.username, password: this.password};
        return this.httpClient
            .post('http://localhost:8080//markit-car-parking/fetch-markit-employees',params)
            .toPromise()
            .then((data: any) => this.userNames = data)
            .catch((err: any) => Promise.resolve());
    }

    getAllMarkitEmployee(): any {
        return this.userNames;
    }
}