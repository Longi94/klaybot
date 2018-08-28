import { Component, OnInit, ViewChild } from '@angular/core';
import { JackboxService } from "../../shared/jackbox.service";
import { JackboxGame } from "../../model/jackbox-game";
import { MatPaginator, MatSort, MatTableDataSource } from "@angular/material";

@Component({
    selector: 'app-history',
    templateUrl: './history.component.html',
    styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

    @ViewChild(MatSort) sort: MatSort;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    dataSource = new MatTableDataSource<JackboxGame>([]);

    columnsToDisplay = ['id', 'code', 'tag', 'server', 'time'];

    constructor(private jackboxService: JackboxService) {
    }

    ngOnInit() {
        this.jackboxService.getJackboxGames().subscribe(games => {
            this.dataSource = new MatTableDataSource<JackboxGame>(games);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        });
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }
}
