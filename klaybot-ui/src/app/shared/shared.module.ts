import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { MatToolbarModule } from "@angular/material";

@NgModule({
    imports: [
        CommonModule,
        MatToolbarModule
    ],
    declarations: [ToolbarComponent],
    exports: [ToolbarComponent]
})
export class SharedModule {
}
