import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LmsSharedModule } from '../../shared';

import {
    ArchiveJobService,
    ArchiveJobPopupService,
    ArchiveJobComponent,
    ArchiveJobDetailComponent,
    ArchiveJobDialogComponent,
    ArchiveJobPopupComponent,
    ArchiveJobDeletePopupComponent,
    ArchiveJobDeleteDialogComponent,
    archiveJobRoute,
    archiveJobPopupRoute,
} from './';

let ENTITY_STATES = [
    ...archiveJobRoute,
    ...archiveJobPopupRoute,
];

@NgModule({
    imports: [
        LmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ArchiveJobComponent,
        ArchiveJobDetailComponent,
        ArchiveJobDialogComponent,
        ArchiveJobDeleteDialogComponent,
        ArchiveJobPopupComponent,
        ArchiveJobDeletePopupComponent,
    ],
    entryComponents: [
        ArchiveJobComponent,
        ArchiveJobDialogComponent,
        ArchiveJobPopupComponent,
        ArchiveJobDeleteDialogComponent,
        ArchiveJobDeletePopupComponent,
    ],
    providers: [
        ArchiveJobService,
        ArchiveJobPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LmsArchiveJobModule {}
