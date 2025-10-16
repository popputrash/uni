#include <esp_task_wdt.h>
#include "pins.h"
#include "random.h"

/*
  Waits for "millis" milliseconds without upsetting the watchdog timer
*/
void waitMs(unsigned int millis)
{
    TickType_t delay = millis / portTICK_PERIOD_MS;
    vTaskDelay(delay);
}

void flashPins(uint8_t a, uint8_t b, uint8_t ms);

void app_main()
{
    initPins();

    while (1)
    {

        flashPins(33, 25, 200);
        int r = getRandommsecs(3000, 5000);
        printf("random value: %d\n", r);
        waitMs(r);

        setLEDA(1);
        setLEDB(1);

        uint8_t winner = 0;
        while (!winner)
        {
            if (isButtonAPressed())
            {
                winner = 1;
            }
            else if (isButtonBPressed())
            {
                winner = 2;
            }
        }

        switch (winner)
        {
        case 1:
            setLEDB(0);
            for (int i = 0; i < 10; i++)
            {
                setLEDA(1);
                waitMs(200);
                setLEDA(0);
                waitMs(200);
            }
            break;

        case 2:
            setLEDA(0);
            for (int i = 0; i < 10; i++)
            {
                setLEDB(1);
                waitMs(200);
                setLEDB(0);
                waitMs(200);
            }
            break;
        }

        waitMs(2000);
    }
}

void flashPins(uint8_t a, uint8_t b, uint8_t ms)
{
    for (int i = 0; i < 10; i++)
    {
        setLEDB(0);
        setLEDA(1);
        waitMs(ms);
        setLEDA(0);
        setLEDB(1);
        waitMs(ms);
    }
    setLEDA(0);
    setLEDB(0);
}
