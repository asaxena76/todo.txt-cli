
const enum FileType {
    'jpg',
    'java',
    'unknown'

};
export class LocalFile {
    constructor(
        public id?: number,
        public localPath?: string,
        public type?: FileType,
        public size?: number,
        public dateAdded?: any,
        public rejected?: boolean,
        public uploaded?: boolean,
        public duplicate?: boolean,
        public hash?: string,
        public resourceUrl?: string,
    ) {
        this.rejected = false;
        this.uploaded = false;
        this.duplicate = false;
    }
}
