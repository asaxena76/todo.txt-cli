import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LmsSharedModule } from '../../shared';

import {
    LocalFileService,
    LocalFilePopupService,
    LocalFileComponent,
    LocalFileDetailComponent,
    LocalFileDialogComponent,
    LocalFilePopupComponent,
    LocalFileDeletePopupComponent,
    LocalFileDeleteDialogComponent,
    localFileRoute,
    localFilePopupRoute,
} from './';

let ENTITY_STATES = [
    ...localFileRoute,
    ...localFilePopupRoute,
];

@NgModule({
    imports: [
        LmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        LocalFileComponent,
        LocalFileDetailComponent,
        LocalFileDialogComponent,
        LocalFileDeleteDialogComponent,
        LocalFilePopupComponent,
        LocalFileDeletePopupComponent,
    ],
    entryComponents: [
        LocalFileComponent,
        LocalFileDialogComponent,
        LocalFilePopupComponent,
        LocalFileDeleteDialogComponent,
        LocalFileDeletePopupComponent,
    ],
    providers: [
        LocalFileService,
        LocalFilePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LmsLocalFileModule {}
