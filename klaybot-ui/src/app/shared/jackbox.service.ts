import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { JackboxGame } from "../model/jackbox-game";
import { environment } from "../../environments/environment";
import { JackboxGameStat } from "../model/jackbox-game-stat";

const BASE_URL = `${environment.serverUrl}api/jackbox`;

@Injectable()
export class JackboxService {

    constructor(private httpClient: HttpClient) {
    }

    getJackboxGames(): Observable<JackboxGame[]> {
        return this.httpClient.get<JackboxGame[]>(`${BASE_URL}/games`);
    }

    getMapping(): Observable<{ [s: string]: string }> {
        return this.httpClient.get<{ [s: string]: string }>(`${BASE_URL}/games/mapping`);
    }

    getStats(): Observable<JackboxGameStat[]> {
        return this.httpClient.get<JackboxGameStat[]>(`${BASE_URL}/games/stats`);
    }

    sendJackboxCode(code: string): Observable<void> {
        const formData = new FormData();
        formData.append('code', code);
        return this.httpClient.post<void>(`${BASE_URL}/games`, formData);
    }
}

