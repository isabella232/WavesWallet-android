package com.wavesplatform.wallet.v2.ui.home.profile.address_book

import com.arellomobile.mvp.InjectViewState
import com.wavesplatform.wallet.v2.data.model.userdb.AddressBookUser
import com.wavesplatform.wallet.v2.ui.base.presenter.BasePresenter
import com.wavesplatform.wallet.v2.util.RxUtil
import com.wavesplatform.wallet.v2.util.queryAllUserData
import io.reactivex.Observable
import javax.inject.Inject

@InjectViewState
class AddressBookPresenter @Inject constructor() : BasePresenter<AddressBookView>() {
    fun getAddresses() {
        addSubscription(Observable.just(queryAllUserData<AddressBookUser>())
                .compose(RxUtil.applyObservableDefaultSchedulers())
                .subscribe({
                    val addresses = it.sortedBy { it.name }.toMutableList()
                    viewState.afterSuccessGetAddress(addresses)
                }, {
                    viewState.afterFailedGetAddress()
                }))
    }
}
