﻿# XGStatScraper

## Overview
XGStatScraper is a Playwright-powered web scraper that extracts football league standings, expected goals (xG) statistics, and live match data (including odds) from **xgscore.io**. The scraped data is stored in an **H2 database** for further analysis. In future updates, the system will send real-time xG changes to users via **WebSockets**.

## Features
-  Scrapes **league standings, expected goals (xG), and match odds**.
-  Extracts **live match updates** with real-time data.
-  Uses **Playwright** for fast and efficient web scraping.
-  Stores data in an **H2 database**.
-  Future update: **WebSocket integration** to notify users about xG changes in real time.

## Technologies Used
- **Java** (Playwright for web scraping)
- **H2 Database** (for lightweight data storage)
- **WebSockets** (planned for real-time updates)

## Installation & Usage
1. **Clone the repository**:
   ```sh
   git clone https://github.com/yourusername/XGStatScraper.git
   cd XGStatScraper
   ```

2. **Build the project**:
   ```sh
   mvn clean install
   ```

3. **Run the scraper**:
   ```sh
   java -jar target/XGStatScraper.jar
   ```

## Future Plans
-  Implement **WebSocket support** for real-time xG updates.
-  Expand support for **more football leagues and competitions**.
-  Optimize performance for even **faster data extraction**.

## License
This project is licensed under the MIT License.
