import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { LocalFile } from './local-file.model';
import { LocalFilePopupService } from './local-file-popup.service';
import { LocalFileService } from './local-file.service';

@Component({
    selector: 'jhi-local-file-delete-dialog',
    templateUrl: './local-file-delete-dialog.component.html'
})
export class LocalFileDeleteDialogComponent {

    localFile: LocalFile;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private localFileService: LocalFileService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['localFile', 'fileType']);
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.localFileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'localFileListModification',
                content: 'Deleted an localFile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-local-file-delete-popup',
    template: ''
})
export class LocalFileDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private localFilePopupService: LocalFilePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.localFilePopupService
                .open(LocalFileDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
