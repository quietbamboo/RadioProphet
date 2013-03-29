//
//  def.h
//  RadioProphet
//
//  Created by Junxian Huang on 3/28/13.
//  Copyright (c) 2013 Junxian Huang. All rights reserved.
//

#ifndef RadioProphet_def_h
#define RadioProphet_def_h

#include <netinet/in.h>
//#include <netinet/ip.h>
//#include <netinet/tcp.h>
//#include <netinet/udp.h>
#include <sys/types.h>
#include "tcp.h"
#include "udp.h"
#include "ip.h"


typedef enum { false, true } bool;
typedef	unsigned long long uint64; //should use this instead of u_long

#define	ETHERTYPE_IP		0x08	/* IP protocol */
static int ETHER_HDR_LEN; //14 for others; 16 for tcpdump linux cooked header
#define IP_HDR_LEN 20
#define UDP_HDR_LEN 8
#define BYTES_PER_32BIT_WORD 4
#define TCP_HDR_LEN
#define TCP_MAX_PAYLOAD 1358
const double BW_MAX_BITS_PER_SECOND = 30000000.0;

#define LOAD_SAMPLE_PERIOD 100 //load sample rate 1 per 100 seconds

static double TIME_BASE = 1350014400.000000; //Thu Oct 12 2012 00:00:00 GMT-0400 (EDT), GMT-4 is the timezone of Atlanta market in the summer when the data is collected

const int USEC_PER_SEC = 1000000;
const double FLOW_MAX_IDLE_TIME = 3600.0;
const double ONE_MILLION = 1000000.0;
const double GVAL_TIME = 3.0;
const double IDLE_THRESHOLD = 1.0; //seconds
const double DUPACK_SLOWSTART_TIME = 0.1; //seconds

static int SAMPLES = 0;
const int SAMPLE_CYCLE = 500;


#endif
