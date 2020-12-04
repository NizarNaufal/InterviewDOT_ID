package id.interview.dotid.view.gallery.support

interface IGalleryIteractor {
    fun getGallery(): Pair<Int, String?>

}

interface IGalleryPresenter {
    fun getGallery()
}
