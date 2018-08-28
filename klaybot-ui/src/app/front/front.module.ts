import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsComponent } from './stats/stats.component';
import { HistoryComponent } from './history/history.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [StatsComponent, HistoryComponent],
    exports: [StatsComponent, HistoryComponent]
})
export class FrontModule {
}
