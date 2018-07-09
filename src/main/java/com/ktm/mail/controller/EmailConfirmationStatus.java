package com.ktm.mail.controller;

public enum EmailConfirmationStatus {
  CONFIRMED,
  INVALID_TOKEN,
  CONFIRMATION_DATE_EXPIRED,
  ALREADY_CONFIRMED
}
