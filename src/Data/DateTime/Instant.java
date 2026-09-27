    // Port of Data/DateTime/Instant.js. Instants are milliseconds since the
    // epoch, stored as Numbers.
    private static long __instantMillis(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        java.time.LocalDateTime moment = java.time.LocalDateTime.of(year, 1, 1, 0, 0)
            .plusMonths(month - 1L).plusDays(day - 1L)
            .plusHours(hour).plusMinutes(minute).plusSeconds(second)
            .plusNanos(millisecond * 1000000L);
        return moment.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
    }

    public static Object fromDateTimeImpl = (java.util.function.Function<Object, Object>) (y) ->
        (java.util.function.Function<Object, Object>) (mo) ->
        (java.util.function.Function<Object, Object>) (d) ->
        (java.util.function.Function<Object, Object>) (h) ->
        (java.util.function.Function<Object, Object>) (mi) ->
        (java.util.function.Function<Object, Object>) (s) ->
        (java.util.function.Function<Object, Object>) (ms) ->
            (double) __instantMillis(
                ((Number) y).intValue(), ((Number) mo).intValue(), ((Number) d).intValue(),
                ((Number) h).intValue(), ((Number) mi).intValue(), ((Number) s).intValue(), ((Number) ms).intValue());

    public static Object toDateTimeImpl = (java.util.function.Function<Object, Object>) (ctor) ->
        (java.util.function.Function<Object, Object>) (instant) -> {
            java.time.LocalDateTime moment = java.time.Instant.ofEpochMilli((long) ((Number) instant).doubleValue())
                .atOffset(java.time.ZoneOffset.UTC).toLocalDateTime();
            return ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ((java.util.function.Function<Object, Object>) ctor)
                .apply(moment.getYear())).apply(moment.getMonthValue())).apply(moment.getDayOfMonth())).apply(moment.getHour())).apply(moment.getMinute())).apply(moment.getSecond())).apply(moment.getNano() / 1000000);
        };
