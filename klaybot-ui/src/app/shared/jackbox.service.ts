import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs/index";
import { JackboxGame } from "../model/jackbox-game";
import { catchError } from "rxjs/internal/operators";
import { environment } from "../../environments/environment";

@Injectable()
export class JackboxService {

    private serverUrl = environment.serverUrl;

    private baseUrl = `${this.serverUrl}api/jackbox`;

    constructor(private httpClient: HttpClient) {
    }

    getJackboxGames(): Observable<JackboxGame[]> {
        return this.httpClient.get<JackboxGame[]>(`${this.baseUrl}/games`);
    }

    sendJackboxCode(code: string): Observable<void> {
        const formData = new FormData();
        formData.append('code', code);
        return this.httpClient.post<void>(`${this.baseUrl}/games`, formData);
    }
}

