package com.wavesplatform.wallet.v2.data.rules

import android.support.annotation.StringRes
import com.wavesplatform.wallet.v1.util.PrefsUtil
import com.wavesplatform.wallet.v2.data.model.userdb.AddressBookUser
import com.wavesplatform.wallet.v2.util.queryFirstUserData

import io.github.anderscheow.validator.rules.BaseRule

class AddressBookAddressRule(var prefsUtil: PrefsUtil, @StringRes errorRes: Int) : BaseRule(errorRes) {

    override fun validate(value: Any?): Boolean {
        if (value == null) {
            throw NullPointerException()
        }

        if (value is String) {
            val user = queryFirstUserData<AddressBookUser> { equalTo("address", value) }
            return user == null
        }

        throw ClassCastException("Required String value")
    }
}