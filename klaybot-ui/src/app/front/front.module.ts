import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsComponent } from './stats/stats.component';
import { HistoryComponent } from './history/history.component';
import {
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule
} from "@angular/material";
import { JackboxService } from "../shared/jackbox.service";
import { ServerPipe } from "./history/server.pipe";
import { CodeComponent } from './code/code.component';
import { ReactiveFormsModule } from "@angular/forms";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatPaginatorModule,
        MatSnackBarModule,
        MatSortModule,
        MatTableModule
    ],
    declarations: [StatsComponent, HistoryComponent, ServerPipe, CodeComponent],
    exports: [StatsComponent, HistoryComponent, CodeComponent],
    providers: [JackboxService]
})
export class FrontModule {
}
