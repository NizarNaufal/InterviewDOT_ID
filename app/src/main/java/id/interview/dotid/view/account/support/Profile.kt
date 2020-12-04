package id.interview.dotid.view.account.support

interface IProfileIteractor {
    fun getProfile(): Pair<Int, String?>
}

interface IProfilePresenter {
    fun getProfile()
}