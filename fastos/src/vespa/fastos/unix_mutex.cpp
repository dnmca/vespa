// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.

#include "mutex.h"
#include <cassert>

FastOS_UNIX_Mutex::FastOS_UNIX_Mutex(void)
    : FastOS_MutexInterface(),
      _mutex()
{
    int error = pthread_mutex_init(&_mutex, nullptr);
    assert(error == 0);
    (void) error;
}

FastOS_UNIX_Mutex::~FastOS_UNIX_Mutex(void)
{
    pthread_mutex_destroy(&_mutex);
}
