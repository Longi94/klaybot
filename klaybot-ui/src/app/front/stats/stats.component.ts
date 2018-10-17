import { Component, OnInit, ViewChild } from '@angular/core';
import { JackboxService } from "../../shared/jackbox.service";
import { forkJoin } from "rxjs";
import { JackboxGameStat } from "../../model/jackbox-game-stat";
import { MatSort, MatTableDataSource } from "@angular/material";
import { JackboxGame } from "../../model/jackbox-game";

@Component({
    selector: 'app-stats',
    templateUrl: './stats.component.html',
    styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {

    @ViewChild(MatSort) sort: MatSort;

    mapping: { [s: string]: string };
    dataSource = new MatTableDataSource<JackboxGameStat>([]);
    columnsToDisplay = ['name', 'count', 'lastPlayed'];

    constructor(private jackboxService: JackboxService) {
    }

    ngOnInit() {
        let mappingRequest = this.jackboxService.getMapping();
        let statsRequest = this.jackboxService.getStats();

        forkJoin([mappingRequest, statsRequest]).subscribe(values => {
            this.mapping = values[0];
            values[1].sort((a, b) => b.count - a.count);
            this.dataSource = new MatTableDataSource<JackboxGameStat>(values[1]);
            this.dataSource.sort = this.sort;
        });
    }

}
