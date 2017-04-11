
const enum ArchiveJobStatus {
    'Configured',
    'Started',
    'Stopped',
    'CompletedError',
    'CompletedSuccess'

};
export class ArchiveJob {
    constructor(
        public id?: number,
        public startTime?: any,
        public status?: ArchiveJobStatus,
        public endTime?: any,
        public dummy?: string,
    ) {
    }
}
