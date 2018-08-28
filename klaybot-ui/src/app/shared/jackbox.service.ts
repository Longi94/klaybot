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
        return this.httpClient.get<JackboxGame[]>(`${this.baseUrl}/games`).pipe(
            catchError(JackboxService.handleError)
        );
    }

    private static handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            console.error('An error occurred:', error.error.message);
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        // return an observable with a user-facing error message
        return throwError(
            'Something bad happened; please try again later.');
    };
}

