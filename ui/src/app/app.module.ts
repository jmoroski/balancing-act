import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { ROUTING } from "./app.routing";
import { BaseModule } from "app/base.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        BaseModule,
        ROUTING
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
