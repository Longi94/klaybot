import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MatButtonModule, MatToolbarModule } from "@angular/material";
import { RouterModule } from "@angular/router";

@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        MatButtonModule,
        MatToolbarModule
    ],
    declarations: [ToolbarComponent],
    exports: [ToolbarComponent]
})
export class SharedModule {
}
