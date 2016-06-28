#include <iostream>
#include <cstdlib>

#include "connection.hpp"
#include "generated/user.h"

/**
  * A test file for playing with the Rapture API
  */

int main(int argc, char* argv[])
{
   std::cout << "Hello world" << std::endl;
   RaptureConnection conn("http://localhost:8665/rapture", "rapture");
   conn.login("rapture");

   UserApi userApi(conn);

   std::cout << userApi.GetWhoAmI() << std::endl;

   return 0;
}
