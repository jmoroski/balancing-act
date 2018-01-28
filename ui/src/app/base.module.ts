import { NgModule } from "@angular/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { HttpClientModule } from "@angular/common/http";
import { ClarityModule } from "clarity-angular";
import { AddressPipe } from "app/common/pipes/address.pipe";
import { CustomCurrencyPipe } from "app/common/pipes/currency.pipe";
import { NamePipe } from "app/common/pipes/name.pipe";
import { CustomDatePipe } from "app/common/pipes/date.pipe";
import { CommonModule } from "@angular/common";

@NgModule({
    declarations: [
        AddressPipe,
        CustomCurrencyPipe,
        NamePipe,
        CustomDatePipe,
    ],
    imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        HttpClientModule,
        ClarityModule
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        HttpClientModule,
        ClarityModule,
        AddressPipe,
        CustomCurrencyPipe,
        NamePipe,
        CustomDatePipe
    ]
})
export class BaseModule {
}
