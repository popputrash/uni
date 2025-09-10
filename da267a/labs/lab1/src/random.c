#include "random.h"
#include "esp_random.h"

int getRandommsecs(int min, int max)
{
    uint32_t rand = esp_random();
    // float r = (float)rand / (float)UINT32_MAX;
    // return min +  r *(max-min);
    return min + rand % (max - min);
}