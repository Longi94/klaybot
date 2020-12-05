import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

const BASE_URL = `${environment.serverUrl}api/chat`;

@Injectable({
    providedIn: 'root'
})
export class ChatService {

    constructor(private httpClient: HttpClient) {
    }

    sendMessage(message: string): Observable<void> {
        const body = {message: message};
        return this.httpClient.post<void>(`${BASE_URL}`, JSON.stringify(body), {
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
}
