import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ArchiveJob } from './archive-job.model';
import { ArchiveJobService } from './archive-job.service';

@Component({
    selector: 'jhi-archive-job-detail',
    templateUrl: './archive-job-detail.component.html'
})
export class ArchiveJobDetailComponent implements OnInit, OnDestroy {

    archiveJob: ArchiveJob;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private archiveJobService: ArchiveJobService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['archiveJob', 'archiveJobStatus']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
        this.registerChangeInArchiveJobs();
    }

    load (id) {
        this.archiveJobService.find(id).subscribe(archiveJob => {
            this.archiveJob = archiveJob;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInArchiveJobs() {
        this.eventSubscriber = this.eventManager.subscribe('archiveJobListModification', response => this.load(this.archiveJob.id));
    }

}
