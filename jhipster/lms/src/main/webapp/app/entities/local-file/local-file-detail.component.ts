import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { LocalFile } from './local-file.model';
import { LocalFileService } from './local-file.service';

@Component({
    selector: 'jhi-local-file-detail',
    templateUrl: './local-file-detail.component.html'
})
export class LocalFileDetailComponent implements OnInit, OnDestroy {

    localFile: LocalFile;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private localFileService: LocalFileService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['localFile', 'fileType']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
        this.registerChangeInLocalFiles();
    }

    load (id) {
        this.localFileService.find(id).subscribe(localFile => {
            this.localFile = localFile;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLocalFiles() {
        this.eventSubscriber = this.eventManager.subscribe('localFileListModification', response => this.load(this.localFile.id));
    }

}
