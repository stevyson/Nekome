package com.chesire.malime.injection

import android.support.test.InstrumentationRegistry.getInstrumentation
import com.chesire.malime.MockApplication
import com.chesire.malime.injection.modules.MockAppModule
import it.cosenonjaviste.daggermock.DaggerMock

fun espressoDaggerMockRule() = DaggerMock.rule<MockComponent>(MockAppModule()) {
    set {
        it.inject(app)
    }
    customizeBuilder<MockComponent.Builder> { it.create(app) }
}

val app: MockApplication
    get() = getInstrumentation().targetContext.applicationContext as MockApplication