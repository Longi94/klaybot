import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { SharedModule } from "./shared/shared.module";
import { RouterModule, Routes } from "@angular/router";
import { StatsComponent } from "./front/stats/stats.component";
import { HistoryComponent } from "./front/history/history.component";
import { FrontModule } from "./front/front.module";

const routes: Routes = [
    {
        path: '',
        redirectTo: 'stats',
        pathMatch: 'full'
    },
    {
        path: 'stats',
        component: StatsComponent,
        pathMatch: 'full'
    },
    {
        path: 'history',
        component: HistoryComponent,
        pathMatch: 'full'
    }
];

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule.forRoot(routes),
        SharedModule,
        FrontModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
