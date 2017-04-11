import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LmsLocalFileModule } from './local-file/local-file.module';
import { LmsArchiveJobModule } from './archive-job/archive-job.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        LmsLocalFileModule,
        LmsArchiveJobModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LmsEntityModule {}
