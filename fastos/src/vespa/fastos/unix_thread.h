// Copyright Yahoo. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
/**
******************************************************************************
* @author  Oivind H. Danielsen
* @date    Creation date: 2000-02-02
* @file
* Class definition for FastOS_UNIX_Thread
*****************************************************************************/

#pragma once

#include "thread.h"

class FastOS_UNIX_Thread : public FastOS_ThreadInterface
{
protected:
    pthread_t _handle;
    bool _handleValid;

    bool Initialize () override;
public:
    FastOS_UNIX_Thread(const FastOS_UNIX_Thread &) = delete;
    FastOS_UNIX_Thread& operator=(const FastOS_UNIX_Thread &) = delete;
    FastOS_UNIX_Thread(FastOS_ThreadPool *pool)
        : FastOS_ThreadInterface(pool),
          _handle(),
          _handleValid(false)
    {}

    ~FastOS_UNIX_Thread() override;

    FastOS_ThreadId GetThreadId () const noexcept override;
    static bool CompareThreadIds (FastOS_ThreadId a, FastOS_ThreadId b);
    static FastOS_ThreadId GetCurrentThreadId ();
};


