import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsComponent } from './stats/stats.component';
import { HistoryComponent } from './history/history.component';
import {
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule
} from "@angular/material";
import { JackboxService } from "../shared/jackbox.service";
import { ServerPipe } from "./history/server.pipe";

@NgModule({
    imports: [
        CommonModule,
        MatFormFieldModule,
        MatInputModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule
    ],
    declarations: [StatsComponent, HistoryComponent, ServerPipe],
    exports: [StatsComponent, HistoryComponent],
    providers: [JackboxService]
})
export class FrontModule {
}
