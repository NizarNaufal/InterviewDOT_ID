package id.interview.dotid.view.wisata.module

interface IWisataIteractor {
    fun getWisata(): Pair<Int, String?>
}

interface IWisataPresenter {
    fun getWisata()
}