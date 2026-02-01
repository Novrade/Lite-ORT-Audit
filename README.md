Lite‑ORT‑Audit
Lite‑ORT‑Audit is a lightweight automation toolkit designed to support Infrastructure ORT (Operational Readiness Testing) performed by the LITE team prior to launching any new MDF.
Its primary goal is to streamline and standardize validation of IT infrastructure within Demarc and MDF/SDEN locations, ensuring that all required components are complete, correctly configured, and compliant with Amazon infrastructure standards.

What Is Infrastructure ORT?
Before a new MDF goes live, the LITE team conducts an Infrastructure Operational Readiness Test — a structured series of technical checks that confirm:

All required hardware is present, installed, and powered correctly
Network configuration is properly implemented
Connectivity and redundancy follow Amazon best practices
Devices, racks, and circuits in Demarc and MDF/SDEN meet operational expectations
The environment is ready for handover to operations without blockers

Lite‑ORT‑Audit provides a modular, script‑driven approach to performing and documenting these validations.

Project Overview
This project includes automation components used during ORT execution, such as:

Infrastructure validation scripts
Connectivity checks and logging
Automated report generation (where applicable)
Modular architecture allowing extension for new ORT test items


Note:
The SQL data source required for certain validation steps is not included in this repository.
It contains site‑specific or internal schema data and must be obtained separately following internal processes.


Key Features

Lightweight and easy to deploy on‑site
Configurable test modules
Automated logging for audit trails
Extensible architecture for new ORT requirements
Focused on reliability and repeatability of infrastructure checks