import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ArchiveJob } from './archive-job.model';
import { ArchiveJobPopupService } from './archive-job-popup.service';
import { ArchiveJobService } from './archive-job.service';

@Component({
    selector: 'jhi-archive-job-delete-dialog',
    templateUrl: './archive-job-delete-dialog.component.html'
})
export class ArchiveJobDeleteDialogComponent {

    archiveJob: ArchiveJob;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private archiveJobService: ArchiveJobService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['archiveJob', 'archiveJobStatus']);
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.archiveJobService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'archiveJobListModification',
                content: 'Deleted an archiveJob'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-archive-job-delete-popup',
    template: ''
})
export class ArchiveJobDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private archiveJobPopupService: ArchiveJobPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.archiveJobPopupService
                .open(ArchiveJobDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
